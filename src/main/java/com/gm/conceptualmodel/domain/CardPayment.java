package com.gm.conceptualmodel.domain;

import javax.persistence.Entity;

import com.gm.conceptualmodel.domain.enums.PaymentStatus;

@Entity
public class CardPayment extends Payment {

	private static final long serialVersionUID = 1L;
	private Integer numberInstallments;

	public CardPayment() {
		super();
	}

	public CardPayment(Integer id, PaymentStatus status, Request request, Integer numberInstallments) {
		super(id, status, request);
		this.numberInstallments = numberInstallments;
	}

	public Integer getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(Integer numberInstallments) {
		this.numberInstallments = numberInstallments;
	}
	
	
}