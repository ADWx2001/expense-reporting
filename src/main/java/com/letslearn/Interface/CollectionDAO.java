package com.letslearn.Interface;

import com.letslearn.Modal.Collection;

import java.util.List;

public interface CollectionDAO {
    void saveCollection(Collection collection);
    double getLastCollectionTotal(String machine);
    double getTotalLast30Days(String machine);
    double getTotalLast365Days(String machine);
    List<Collection> getCollections(String machine, String startDate, String endDate);
}
