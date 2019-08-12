package com.lbc.mo.dao;

import com.lbc.mo.entity.AppState;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class AppStateSpecifications {

    public static class StartTimeBetweenSpec implements Specification<AppState> {

        private Date sTime;

        private Date eTime;

        public StartTimeBetweenSpec(Date sTime, Date eTime) {
            this.sTime = sTime;
            this.eTime = eTime;
        }

        @Override
        public Predicate toPredicate(Root<AppState> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.between(root.get("startTime"), sTime, eTime);
        }
    }

    public static class StartTimeEqualSpec implements Specification<AppState> {
        private Date sTime;

        public StartTimeEqualSpec(Date sTime) {
            this.sTime = sTime;
        }

        @Override
        public Predicate toPredicate(Root<AppState> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.equal(root.get("startTime"), sTime);
        }
    }

}
