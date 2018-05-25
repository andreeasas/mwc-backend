/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.utils.datastage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public abstract class WithSessionAndTransaction<T> {
  protected T returnValue;

  public T run() {
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();

    executeBusinessLogic(session);

    tx.commit();
    session.close();

    return returnValue;
  }

  protected abstract void executeBusinessLogic(Session session);

  public void setReturnValue(T returnValue) {
    this.returnValue = returnValue;
  }

}
