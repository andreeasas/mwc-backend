/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.mwc.utils.datastage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.mwc.domain.Category;
import com.mwc.domain.Cost;
import com.mwc.domain.Member;
import com.mwc.domain.MonetaryUnit;
import com.mwc.domain.User;
import com.mwc.utils.consts.CategoryConsts;
import com.mwc.utils.consts.HouseConsts;




/**
 * @author <a href="mailto:asas@ssi-schaefer-noell.com">asas</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class DemoDataStage {

  public static void main(String[] args) {

    WithSessionAndTransaction<Object> insertData = new WithSessionAndTransaction<Object>() {

      @Override
      protected void executeBusinessLogic(Session session) {

        /** ---CREATE USERS--- */
        User admin = createUser("admin", "admin", null);
//        Member minion = new Member("minion");
//        User asa = createUser("asa", "demo", Arrays.asList(new Member("andreea"), minion));
        User asa = createUser("asa", "asa", null);

        /** ---CREATE MONETARY UNITS--- */
        MonetaryUnit ron = new MonetaryUnit("RON", "ron");
        MonetaryUnit euro = new MonetaryUnit("EURO", "euro");
        
        /** ---CREATE DEFAULT CATEGORIES--- */
        String[] categoriesName = new String[] { //
          CategoryConsts.COMMUNICATION, //
          CategoryConsts.DEBT, //
          CategoryConsts.EDUCATION, //
          CategoryConsts.FOOD, //
          CategoryConsts.HOUSE, //
          CategoryConsts.PERSONAL, //
          CategoryConsts.RECREATION, //
          CategoryConsts.TRANSPORT //
        };
        List<Category> categories = createCategoriesForUser(admin, categoriesName);

        /** ---CREATE DEFAULT SUBCATEGORIES--- */

        /** ---CREATE HOUSE'S SUBCATEGORIES--- */
        Category house = findCategory(CategoryConsts.HOUSE, categories);
        String[] houseSubcategories = new String[] { //
          HouseConsts.BILLS, //
          HouseConsts.EQUIPMENT, //
          HouseConsts.MAINTENANCE //
        };
        // automatically saven when saving the categories
        List<Category> houseCategories = createSubCategories(house, houseSubcategories);

        /** ---CREATE EXAMPLE COSTS--- */
        Category bill = findCategory(HouseConsts.BILLS, houseCategories);
//        LocalDate dateIan = LocalDate.of(2018, Month.JANUARY, 23);
        @SuppressWarnings("deprecation")
		Date dateIan = new Date(2018, 1, 23);
        Cost enelBill = new Cost(asa, null, bill, ron, new Double(45.70), dateIan, "facura Enel");

        Category equipment = findCategory(HouseConsts.EQUIPMENT, houseCategories);
//        LocalDate dateFeb = LocalDate.of(2018, Month.FEBRUARY, 8);
        @SuppressWarnings("deprecation")
		Date dateFeb = new Date(2018, 2, 8);
        Cost gasCooker = new Cost(asa, null, equipment, ron, new Double(740.50), dateFeb, "aragaz");

        /** ---SAVE ENTITIES--- */
        // users also save the associated members and categories
        saveEntities(session, Arrays.asList(admin, asa));
        saveEntities(session, Arrays.asList(ron, euro));
        saveEntities(session, Arrays.asList(enelBill, gasCooker));

      }

      private void saveEntities(Session session, List<Object> entities) {
        entities.forEach(entity -> {
          session.save(entity);
        });
      }

    };

    insertData.run();

  }

  private static Category findCategory(String categName, List<Category> categories) {
    for (Category category : categories) {
      if (category.getName().equals(categName)) {
        return category;
      }
    }
    return null;
  }

  /**
   * @param user
   * @param subcategoriesName
   * @return 
   */
  private static List<Category> createCategoriesForUser(User user, String[] subcategoriesName) {
    List<Category> categories = new ArrayList<Category>();
    for (String categName : subcategoriesName) {
      categories.add(new Category(categName, null, user, null));
    }
    // categories will be automatically saved when saving the user
    if (user != null) {
      user.setCategories(categories);
    }
    return categories;
  }

  private static List<Category> createSubCategories(Category parent, String[] categoriesName) {
    List<Category> subcategories = new ArrayList<Category>();
    for (String categName : categoriesName) {
      subcategories.add(new Category(categName, parent, parent.getDbUser(), null));
    }
    // subcategories will be automatically saved when saving the categories
    if (parent != null) {
      parent.setSubcategories(subcategories);
    }
    return subcategories;
  }

  protected static List<Category> createDefaultCategories(User user, String[] categNames) {
    List<Category> categories = new ArrayList<Category>();
    for (String categName : categNames) {
      categories.add(new Category(categName, null, user, null));
    }
    return categories;
  }

  private static User createUser(String username, String password, List<Member> members) {
    User user = new User(username, password, password);
    if (members == null) {
      return user;
    }

    // for setting connection between user and members. 
    // Member = owner, so connection is only set by member.setUser(..); user.setMembers(..) won't set fk in db. 
    members.forEach(member -> {
      member.setUser(user);
    });

    // for saving the members automatically when saving the user (through cascade)
    user.setMembers(members);
    return user;
  }

}
