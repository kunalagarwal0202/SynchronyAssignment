package com.synchrony.imageapi.repository;

import com.synchrony.imageapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByImgurlImageId(String imgurlImageId);

    void deleteByImgurlImageId(String imgurlImageId);
}
