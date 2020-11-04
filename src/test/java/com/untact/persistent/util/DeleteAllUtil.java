package com.untact.persistent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.untact.persistent.attendance.AttendanceRepository;
import com.untact.persistent.board.BoardRepository;
import com.untact.persistent.connectionrecord.ConnectionRecordRepository;
import com.untact.persistent.deposit.DepositRepository;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.file.FileEntityRepository;
import com.untact.persistent.fine.FineRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.grouprule.GroupRuleRepository;
import com.untact.persistent.incorrectanswernote.IncorrectAnswerNoteRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.reply.ReplyRepository;
import com.untact.persistent.representativetimetableitem.RepresentativeTimeTableItemRepository;
import com.untact.persistent.score.ScoreRepository;
import com.untact.persistent.timetable.TimeTableRepository;
import com.untact.persistent.timetableitem.TimeTableItemRepository;
import com.untact.persistent.vocabulary.VocabularyRepository;
import com.untact.persistent.wordincorrectanswernote.WordIncorrectAnswerNoteRepository;

@Component
public class DeleteAllUtil {
	@Autowired
	private AttendanceRepository attendanceRepo;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private ConnectionRecordRepository connectionRecordRepo;
	@Autowired
	private DepositRepository depositRepo;
	@Autowired
	private EnglishDictionaryRepository englishDictionaryRepo;
	@Autowired
	private EnglishSpellingRepository englishSpellingRepo;
	@Autowired
	private FileEntityRepository fileRepo;
	@Autowired
	private FineRepository fineRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Autowired
	private GroupRuleRepository groupRuleRepo;
	@Autowired
	private IncorrectAnswerNoteRepository incorrectAnswerNoteRepo;
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private ReplyRepository replyRepo;
	@Autowired
	private RepresentativeTimeTableItemRepository representativeTimeTableItemRepo;
	@Autowired
	private ScoreRepository scoreRepo;
	@Autowired
	private TimeTableItemRepository timeTableItemRepo;
	@Autowired
	private TimeTableRepository timeTableRepo;
	@Autowired
	private VocabularyRepository vocabularyRepo;
	@Autowired
	private WordIncorrectAnswerNoteRepository wordIncorrectAnswerNoteRepo;
	public void deleteAllRepo() {
		attendanceRepo.deleteAllInBatch();
		representativeTimeTableItemRepo.deleteAllInBatch();
		timeTableItemRepo.deleteAllInBatch();
		timeTableRepo.deleteAllInBatch();
		connectionRecordRepo.deleteAllInBatch();
		depositRepo.deleteAllInBatch();
		groupRuleRepo.deleteAllInBatch();
		incorrectAnswerNoteRepo.deleteAllInBatch();
		wordIncorrectAnswerNoteRepo.deleteAllInBatch();
		scoreRepo.deleteAllInBatch();
		fineRepo.deleteAllInBatch();
		replyRepo.deleteAllInBatch();
		fileRepo.deleteAllInBatch();
		boardRepo.deleteAllInBatch();
		groupIncludeRepo.deleteAllInBatch();
		vocabularyRepo.deleteAllInBatch();
		englishDictionaryRepo.deleteAllInBatch();
		englishSpellingRepo.deleteAllInBatch();
		memberRepo.deleteAllInBatch();
		groupRepo.deleteAllInBatch();
	}
}
