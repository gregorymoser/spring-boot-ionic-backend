package com.gm.conceptualmodel.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gm.conceptualmodel.domain.enums.PaymentStatus;

@Entity
public class BillPayment extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	private Date payday;

	public BillPayment() {
		super();
	}

	public BillPayment(Integer id, PaymentStatus status, Request request, Date dueDate, Date payday) {
		super(id, status, request);
		this.dueDate = dueDate;
		this.payday = payday;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayday() {
		return payday;
	}

	public void setPayday(Date payday) {
		this.payday = payday;
	}

}