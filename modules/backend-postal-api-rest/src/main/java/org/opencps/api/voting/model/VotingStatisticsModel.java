//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.25 at 09:54:05 AM ICT 
//


package org.opencps.api.voting.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="templateNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="votingsCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="answersCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="usersCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="answers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "templateNo",
    "subject",
    "choices",
    "votingsCount",
    "answersCount",
    "usersCount",
    "answers"
})
@XmlRootElement(name = "VotingStatisticsModel")
public class VotingStatisticsModel {

    protected String templateNo;
    protected String subject;
    protected String choices;
    protected Long votingsCount;
    protected Long answersCount;
    protected Long usersCount;
    protected String answers;

    /**
     * Gets the value of the templateNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateNo() {
        return templateNo;
    }

    /**
     * Sets the value of the templateNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateNo(String value) {
        this.templateNo = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the choices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChoices() {
        return choices;
    }

    /**
     * Sets the value of the choices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChoices(String value) {
        this.choices = value;
    }

    /**
     * Gets the value of the votingsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVotingsCount() {
        return votingsCount;
    }

    /**
     * Sets the value of the votingsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVotingsCount(Long value) {
        this.votingsCount = value;
    }

    /**
     * Gets the value of the answersCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAnswersCount() {
        return answersCount;
    }

    /**
     * Sets the value of the answersCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAnswersCount(Long value) {
        this.answersCount = value;
    }

    /**
     * Gets the value of the usersCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUsersCount() {
        return usersCount;
    }

    /**
     * Sets the value of the usersCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUsersCount(Long value) {
        this.usersCount = value;
    }

    /**
     * Gets the value of the answers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswers() {
        return answers;
    }

    /**
     * Sets the value of the answers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswers(String value) {
        this.answers = value;
    }

}
