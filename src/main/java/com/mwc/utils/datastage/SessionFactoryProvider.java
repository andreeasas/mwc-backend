/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.utils.datastage;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class SessionFactoryProvider {

  private static SessionFactory factory;

  public static SessionFactory getSessionFactory() {
    if (factory == null) {
      try {
        //        Configuration configuration = new Configuration().configure();
        //        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        //        factory = configuration.buildSessionFactory(builder.build());

        factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
        System.err.println("Failed to create sessionFactory object." + ex);
      }
    }

    return factory;
  }

}
