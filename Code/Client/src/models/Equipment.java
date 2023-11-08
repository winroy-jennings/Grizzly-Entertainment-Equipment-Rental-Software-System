public class Equipment {
	//Equipment Attributes
	String category;
	date dateAvailable
	double cost;
	int Id;
	String equipmentType;
	String rentalStatus;
	
	public Equipment()
	{
		category = "";
		date = new dateAvailable();
		cost = 0.0;
		Id = 00;
		equipmentType = "";
		rentalStatus = "";
	}
	public Equipment(String category, date dateAvailabale, double cost, int Id, String equipmentType, String rentalStatus)
	{
		this.category = category;
		this.date = dateAvailabale;
		this.cost = cost;
		this.Id = tId;
		this.equipmentType = equipmentType;
		this.rentalStatus = rentalStatus;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public date getDate() {
		return dateAvailable;
	}
	public void setDate(date dateAvailable) {
		this.date = dateAvailable;
	}
	public double getcost() {
		return cost;
	}
	public void setEquipmentCost(double cost) {
		this.cost = cost;
	}
	public int getId() {
		return Id;
	}
	public void setEquipmentId(int Id) {
		this.Id = Id;
	}
	public String getEquipmentName() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	@Override
	public String toString() {
		return "Equipment [category=" + category + ", equipmentCost=" + cost + ", equipmentId=" + Id
				+ ", equipmentName=" + equipmentType + ", rentalStatus=" + rentalStatus + "]";
	}
	void display()
	{
		toString();
	}
	
	

}
