package com.au.example.config;

import com.au.example.data.listener.CheckEntityDataTypeCheckListener;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateEventConfig {
  private final EntityManagerFactory entityManagerFactory;

  public HibernateEventConfig(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @PostConstruct
  public void registerListeners() {
    SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
    EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
    assert registry != null;
    registry.getEventListenerGroup(EventType.PRE_INSERT).appendListeners(new CheckEntityDataTypeCheckListener());
  }
}
