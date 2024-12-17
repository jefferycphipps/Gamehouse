package com.gamehouse.project.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;


    @MappedSuperclass
    public abstract class AbstractEntity {

        @Id
        @GeneratedValue
        private int id;


        @NotBlank(message = "Name is required")
        private String name;

        public int getId() {
            return id;
        }

        public @NotBlank(message = "Name is required") String getName() {
            return name;
        }

        public void setName(@NotBlank(message = "Name is required") String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractEntity that = (AbstractEntity) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

