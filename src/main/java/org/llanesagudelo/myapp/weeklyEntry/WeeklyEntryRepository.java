package org.llanesagudelo.myapp.weeklyEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeeklyEntryRepository extends JpaRepository<WeeklyEntry, UUID> {
}
