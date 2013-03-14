package com.ace.salesail.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This is the base class for all the objects on the domain
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Persistent implements Serializable {

    protected Long id;
    protected Date dateCreated = GregorianCalendar.getInstance().getTime();
    protected Date dateUpdated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
