package com.graduation.voting.util;

import com.graduation.voting.model.AbstractBaseEntity;
import com.graduation.voting.util.exception.NotFoundException;
import com.graduation.voting.util.exception.VoteException;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.LocalTime.*;

public class ValidationUtil {

    public final static LocalTime EXPIRED_TIME = of(11, 00); // 11 00

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static void checkDateTime(LocalDate date, LocalTime time) {
        LocalDate today = LocalDate.now();
        if (!today.isEqual(date)) {
            throw new VoteException("You voted on " + date + ". You can not change it today " + today);
        }
        checkTime(time);
    }

    public static void checkTime(LocalTime time) {
        if (EXPIRED_TIME.isBefore(time)) {
            throw new VoteException("it is after 11:00. it is too late, vote can't be changed");
        }
    }

    public static boolean isTimeExpired() {
        return EXPIRED_TIME.isBefore(now());
    }
}
