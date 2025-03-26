package com.au.example.data.listener;

import java.lang.reflect.Field;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

public class CheckEntityDataTypeCheckListener implements PreInsertEventListener {

  @Override
  public boolean onPreInsert(PreInsertEvent event) {
    Object entity = event.getEntity();
    Object[] state = event.getState();
    String[] propertyNames = event.getPersister().getPropertyNames();
    Class<?> entityClass = entity.getClass();

    try {
      for (int i = 0; i < propertyNames.length; i++) {
        Field field = entityClass.getDeclaredField(propertyNames[i]);
        if (field.getType().equals(String.class)) {
          field.setAccessible(true);
          Object currentValue = field.get(entity);
          String newValue = trimIfLengthLongerThan((String) currentValue, 10);
          field.set(entity, newValue);
          state[i] = newValue;
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  private String trimIfLengthLongerThan(String str, int length) {
    if (str.length() > length) {
      return str.substring(0, length);
    }
    return str;
  }
}
