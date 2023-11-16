package models;

public class EquipmentInventory {
	private String category;
	private int id;
	private String equipmentType;
	private int equipmentQuantity;
	
	public EquipmentInventory()
	{
		category = "";
		id = 0;
		equipmentType = "";
		equipmentQuantity = 0;
	}
	
	public EquipmentInventory(String category, int id, String equipmentType,int equipmentQuantity) {
		this.category = category;
		this.id = id;
		this.equipmentType = equipmentType;
		this.equipmentQuantity = equipmentQuantity;
	}
	public EquipmentInventory (EquipmentInventory equip) {
		this.category = equip.category;
		this.id = equip.id;
		this.equipmentType = equip.equipmentType;
		this.equipmentQuantity = equip.equipmentQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public int getEquipmentQuantity() {
		return equipmentQuantity;
	}

	public void setEquipmentQuantity(int equipmentQuantity) {
		this.equipmentQuantity = equipmentQuantity;
	}

	@Override
	public String toString() {
		return "EquipmentInventory [category=" + category + ", id=" + id + ", equipmentType=" + equipmentType
				+ ", equipmentQuantity=" + equipmentQuantity + "]";
	}
	
}
