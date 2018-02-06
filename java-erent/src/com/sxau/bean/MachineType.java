package com.sxau.bean;

public class MachineType {

	/*
	 *  type_id            number               not null,
   type_name          varchar2(20),
   type_code          varchar2(50),
   type_sort          number,
   parent_id          number,
   type_status        INTEGER,
	 */
	private int typeId;
	private String typeName;
	private String typeCode;
	private int typeSort;
	private Integer typeStatus;
	
	private String typeRemark;
	
	private int parentId;
	private MachineType parent;
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
    public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public int getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(int typeSort) {
		this.typeSort = typeSort;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Integer getTypeStatus() {
		return typeStatus;
	}
	public String getTypeStatusText() {
		return typeStatus == 0 ? "启用":"禁用";
	}
	public void setTypeStatus(Integer typeStatus) {
		this.typeStatus = typeStatus;
	}
	public MachineType getParent() {
		return parent;
	}
	public void setParent(MachineType parent) {
		this.parent = parent;
	}
	public String getTypeRemark() {
		return typeRemark;
	}
	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}
	
	
}
