package com.journal.services;

import com.journal.entities.Point;
import com.journal.repositories.PointRepository;
import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService implements Insertable<Point> {

    private final PointRepository pointRepository;

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public boolean InsertEntity(Point entity) {
        pointRepository.save(entity);
        return true;
    }
}
