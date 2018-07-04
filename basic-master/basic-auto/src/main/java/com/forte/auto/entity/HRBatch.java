package com.forte.auto.entity;

import java.util.Date;

public class HRBatch {
	private int id;
	private String batch_date;
	private String procedure_name;
	private Date execute_begin_date;
	private Date execute_end_date;
	private String execute_state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatch_date() {
		return batch_date;
	}
	public void setBatch_date(String batch_date) {
		this.batch_date = batch_date;
	}
	public String getProcedure_name() {
		return procedure_name;
	}
	public void setProcedure_name(String procedure_name) {
		this.procedure_name = procedure_name;
	}
	public Date getExecute_begin_date() {
		return execute_begin_date;
	}
	public void setExecute_begin_date(Date execute_begin_date) {
		this.execute_begin_date = execute_begin_date;
	}
	public Date getExecute_end_date() {
		return execute_end_date;
	}
	public void setExecute_end_date(Date execute_end_date) {
		this.execute_end_date = execute_end_date;
	}
	public String getExecute_state() {
		return execute_state;
	}
	public void setExecute_state(String execute_state) {
		this.execute_state = execute_state;
	}
	
}
