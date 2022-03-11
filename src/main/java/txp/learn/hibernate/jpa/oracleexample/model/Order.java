package txp.learn.hibernate.jpa.oracleexample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Order {

	private Long id;
	private Integer discount;
	private Date lastUpdate;
	private String shipmentInfo;
	private Character status;
	
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue(generator="increment")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE")
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Column(name = "SHIPMENT_INFO")
	public String getShipmentInfo() {
		return shipmentInfo;
	}
	public void setShipmentInfo(String shipmentInfo) {
		this.shipmentInfo = shipmentInfo;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	
}
