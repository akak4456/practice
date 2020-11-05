package com.untact.persistent.representativetimetableitem;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.representativetimetableitem.RepresentativeTimeTableItem;

public interface RepresentativeTimeTableItemRepository
		extends JpaRepository<RepresentativeTimeTableItem, Long>, RepresentativeTimeTableItemCustomRepository {
	@Modifying
	@Transactional
	@Query("DELETE FROM RepresentativeTimeTableItem i where i.representativeTimeTable.rtno=:rtno")
	public int deleteByRepresentativeTimeTableNumber(@Param("rtno")Long rtno);
	
	
	@Query("SELECT i FROM RepresentativeTimeTableItem i WHERE i.representativeTimeTable.rtno=:rtno")
	public List<RepresentativeTimeTableItem> findByRepresentativeTimeTableNumber(@Param("rtno")Long rtno);
	
}
