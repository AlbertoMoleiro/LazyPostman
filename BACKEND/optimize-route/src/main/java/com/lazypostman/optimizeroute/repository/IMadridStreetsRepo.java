package com.lazypostman.optimizeroute.repository;

import com.lazypostman.optimizeroute.entity.MadridStreets;
import com.lazypostman.optimizeroute.model.ItinineraryItemProjection;
import com.lazypostman.optimizeroute.model.formcreator.RoadProjection;
import com.lazypostman.optimizeroute.model.formcreator.TownProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IMadridStreetsRepo extends JpaRepository<MadridStreets, Long> {


    @Query(value = "SELECT DISTINCT cdmuni, dsmuni FROM madrid_streets", nativeQuery = true)
    Set<TownProjection> getTowns();

    @Query(value = "SELECT dsvial_nor AS name, " +
            "MIN(CASE WHEN MOD(numero, 2) = 1 THEN numero END) AS minOdd, " +
            "MAX(CASE WHEN MOD(numero, 2) = 1 THEN numero END) AS maxOdd, " +
            "MIN(CASE WHEN MOD(numero, 2) = 0 THEN numero END) AS minEven, " +
            "MAX(CASE WHEN MOD(numero, 2) = 0 THEN numero END) AS maxEven " +
            "FROM madrid_streets " +
            "WHERE cdmuni = :codigo " +
            "GROUP BY dsvial_nor",
            nativeQuery = true)
    List<RoadProjection> getRoadData(@Param("codigo") Integer codigo);

    //Rangos de números entre minimos y máximos dependidendo de si es par o no
    @Query("SELECT m.dsmuni AS town,m.cdtvia AS roadType,m.dsvialNor AS roadName, m.numero AS numero,m.coordX AS coordX, m.coordY AS coordY " +
            "FROM MadridStreets m " +
            "WHERE MOD(m.numero, 2) != 0 AND m.numero BETWEEN :start AND :end AND m.cdmuni = :cdmuni AND m.dsvialNor = :dsvialNor AND m.cdtvia = :cdtvia")
    List<ItinineraryItemProjection> findOddNumberCoordsBetween(@Param("start") Integer start, @Param("end") Integer end, @Param("cdmuni") Integer cdmuni, @Param("dsvialNor") String dsvialNor, @Param("cdtvia") String cdtvia);

    @Query("SELECT m.dsmuni AS town,m.cdtvia AS roadType,m.dsvialNor AS roadName, m.numero AS numero,m.coordX AS coordX, m.coordY AS coordY " +
            "FROM MadridStreets m " +
            "WHERE MOD(m.numero, 2) = 0 AND m.numero BETWEEN :start AND :end AND m.cdmuni = :cdmuni AND m.dsvialNor = :dsvialNor AND m.cdtvia = :cdtvia")
    List<ItinineraryItemProjection> findEvenNumberCoordsBetween(@Param("start") Integer start, @Param("end") Integer end, @Param("cdmuni") Integer cdmuni, @Param("dsvialNor") String dsvialNor, @Param("cdtvia") String cdtvia);

    //Rango de numeros entro dos numeros
    @Query("SELECT m.dsmuni AS town,m.cdtvia AS roadType,m.dsvialNor AS roadName, m.numero AS numero,m.coordX AS coordX, m.coordY AS coordY " +
            "FROM MadridStreets m " +
            "WHERE m.numero BETWEEN :start AND :end AND m.cdmuni = :cdmuni AND m.dsvialNor = :dsvialNor AND m.cdtvia = :cdtvia")
    List<ItinineraryItemProjection> findCoordsBetween(@Param("start") Integer start, @Param("end") Integer end, @Param("cdmuni") Integer cdmuni, @Param("dsvialNor") String dsvialNor, @Param("cdtvia") String cdtvia);

}
