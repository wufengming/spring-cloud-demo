/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wfm.serviceactiv.activiti.modeler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Tijs Rademakers
 * 编辑器制图之后，将节点信息以json的形式提交给这个Controller，然后由其进行持久化操作
 */
@Slf4j
@RestController
@RequestMapping("/act")
@Transactional
public class ModelSaveRestResource implements ModelDataJsonConstants {

  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.OK)
  public void saveModel(@PathVariable String modelId,
                        @RequestParam String name,
                        @RequestParam String description,
                        @RequestParam String json_xml,
                        @RequestParam String svg_xml) {
    try {
      
      Model model = repositoryService.getModel(modelId);
      
      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

      int newVersion = model.getVersion()+1;
      modelJson.put(MODEL_NAME, name);
      modelJson.put(MODEL_DESCRIPTION, description);
      modelJson.put(MODEL_REVISION, newVersion);

      model.setMetaInfo(modelJson.toString());
      model.setName(name);
      model.setVersion(newVersion);
      repositoryService.saveModel(model);
      
      repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
      
      InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
      TranscoderInput input = new TranscoderInput(svgStream);
      
      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);
      
      // Do the transformation
      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();


      //保存模板信息
      
    } catch (Exception e) {
      log.error("保存模型出错", e);
      throw new ActivitiException("保存模型出错", e);
    }
  }
}
