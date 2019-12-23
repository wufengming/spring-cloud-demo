package ${package.Entity?replace('.entity','')}.model.vo.${entity?replace('Entity','')?lower_case};


<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.experimental.Accessors;
</#if>
import java.io.Serializable;

/**
* <p>
* ${table.comment!} 查询结果对象
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
@Accessors(chain = true)
</#if>
@ApiModel(value="${entity?replace('Entity','')}QueryVo对象", description="${table.comment!} 查询参数")
public class ${entity?replace('Entity','')}QueryVo implements Serializable {
  private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private Long id;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

    @ApiModelProperty(value = "版本号")
    private  Integer recordVer;

<#if entityColumnConstant>
    <#list table.fields as field>
        public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>

<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}
