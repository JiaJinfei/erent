package com.sxau.bean;

public class Machine {

	/*machine_id         number               not null,
	   machine_name       varchar2(20),
	   machine_price      FLOAT(10),
	   machine_type       number,
	   machine_code       varchar2(20),
	   machine_status     INTEGER,
	   machine_num        number(10),
	   machine_remark     varchar2(1000),
	   machine_pic        varchar2(50),
	   machine_pic2       varchar2(50),*/
	private int machineId;
	private String machineName;
	private double machinePrice;
	
	private int typeId;
	private MachineType type;
	
	
	private String machineCode;
	private Integer machineStatus;
	private int machineNum;
	private String machineRemark;
	private String machinePic;
	private String machinePic2;
	
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public double getMachinePrice() {
		return machinePrice;
	}
	public void setMachinePrice(double machinePrice) {
		this.machinePrice = machinePrice;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public Integer getMachineStatus() {
		return machineStatus;
	}
	public String getMachineStatusText() {
		return machineStatus == 0 ? "空闲" : "工作中";
	}
	
	public void setMachineStatus(Integer machineStatus) {
		this.machineStatus = machineStatus;
	}
	public int getMachineNum() {
		return machineNum;
	}
	public void setMachineNum(int machineNum) {
		this.machineNum = machineNum;
	}
	public String getMachineRemark() {
		return machineRemark;
	}
	public void setMachineRemark(String machineRemark) {
		this.machineRemark = machineRemark;
	}
	public String getMachinePic() {
		return machinePic;
	}
	public void setMachinePic(String machinePic) {
		this.machinePic = machinePic;
	}
	public String getMachinePic2() {
		return machinePic2;
	}
	public void setMachinePic2(String machinePic2) {
		this.machinePic2 = machinePic2;
	}
	public MachineType getType() {
		return type;
	}
	public void setType(MachineType type) {
		this.type = type;
	}
}
