package com.au.example.data.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

  @Id
  @GeneratedValue
  Long id;
  String title;
  boolean done;

  @CreationTimestamp(source = SourceType.DB)
  Instant createdOn;

  @UpdateTimestamp(source = SourceType.DB)
  Instant lastUpdatedOn;
}
