package com.github.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.math.BigDecimal;

import javax.persistence.*;
import java.sql.Date;
import java.time.Duration;

import java.util.List;


@Entity
@Table(name = "credit_application")
//@TypeDef(name="interval", typeClass = Interval.class)
public class CreditApplication {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="client_id",referencedColumnName = "id")
    private Client client;
    @Column(name="period_of_employment_start")
    private Date period_of_employment_start;
    @Column(name="period_of_employment_end")
    private Date period_of_employment_end;
    @Column(name="position_at_work")
    private String position_at_work;
    @Column(name="name_of_organization")
    private String  name_of_organization;
    @Column(name="amount_of_credit")
    private BigDecimal amount_of_credit;

    @OneToOne(mappedBy = "application")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private CreditDecision decision;
    @OneToOne(mappedBy = "application_final")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private CreditAgreement agreement;

    public CreditApplication() {}

    public CreditApplication(Client client, Date period_of_employment_start, Date period_of_employment_end, String position_at_work, String name_of_organization, BigDecimal amount_of_credit) {
        this.client = client;
        this.period_of_employment_start = period_of_employment_start;
        this.period_of_employment_end = period_of_employment_end;
        this.position_at_work = position_at_work;
        this.name_of_organization = name_of_organization;
        this.amount_of_credit = amount_of_credit;
    }

    public void setDecision(CreditDecision decision){
        this.decision=decision;
        decision.setApplication(this);
    }

    public void setAgreement(CreditAgreement agreement){
        this.agreement=agreement;
        agreement.setApplication_final(this);
    }
}
