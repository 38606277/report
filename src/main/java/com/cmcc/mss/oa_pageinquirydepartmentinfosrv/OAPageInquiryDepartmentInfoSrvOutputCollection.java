
package com.cmcc.mss.oa_pageinquirydepartmentinfosrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OA_PageInquiryDepartmentInfoSrvOutputCollection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OA_PageInquiryDepartmentInfoSrvOutputCollection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OA_PageInquiryDepartmentInfoSrvOutputItem" type="{http://mss.cmcc.com/OA_PageInquiryDepartmentInfoSrv}OA_PageInquiryDepartmentInfoSrvOutputItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OA_PageInquiryDepartmentInfoSrvOutputCollection", propOrder = {
    "oaPageInquiryDepartmentInfoSrvOutputItem"
})
public class OAPageInquiryDepartmentInfoSrvOutputCollection {

    @XmlElement(name = "OA_PageInquiryDepartmentInfoSrvOutputItem")
    protected List<OAPageInquiryDepartmentInfoSrvOutputItem> oaPageInquiryDepartmentInfoSrvOutputItem;

    /**
     * Gets the value of the oaPageInquiryDepartmentInfoSrvOutputItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oaPageInquiryDepartmentInfoSrvOutputItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOAPageInquiryDepartmentInfoSrvOutputItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OAPageInquiryDepartmentInfoSrvOutputItem }
     * 
     * 
     */
    public List<OAPageInquiryDepartmentInfoSrvOutputItem> getOAPageInquiryDepartmentInfoSrvOutputItem() {
        if (oaPageInquiryDepartmentInfoSrvOutputItem == null) {
            oaPageInquiryDepartmentInfoSrvOutputItem = new ArrayList<OAPageInquiryDepartmentInfoSrvOutputItem>();
        }
        return this.oaPageInquiryDepartmentInfoSrvOutputItem;
    }

}
