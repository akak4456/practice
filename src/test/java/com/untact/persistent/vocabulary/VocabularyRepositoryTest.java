package com.untact.persistent.vocabulary;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.member.Role;
import com.untact.domain.vocabulary.Vocabulary;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.util.DefaultEnglishSpellingAndDictionaryUtil;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class VocabularyRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	@Autowired
	private DefaultEnglishSpellingAndDictionaryUtil dictionaryUtil;
	@Autowired
	private GroupEntityRepository groupEntityRepo;
	@Autowired
	private MemberEntityRepository memberEntityRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Autowired
	private VocabularyRepository vocabularyRepo;
	@Autowired
	private EnglishSpellingRepository spellingRepo;
	
	private MemberEntity member;
	private GroupEntity group;
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
		
		dictionaryUtil.setUpDefaultDictionary();
		
		member = new MemberEntity().builder().email("email").password("password").role(Role.MEMBER).build();
		group = new GroupEntity().builder().title("title").build();
		
		memberEntityRepo.save(member);
		groupEntityRepo.save(group);
		
		groupIncludeRepo.save(new GroupInclude().builder().group(group).member(member).build());
	}
	
	@Test
	public void initTest() {
		
	}
	@Test
	public void deleteByGroupNumberAndMemberNumberAndWordListTest() {
		/*
		 english_spelling entity에
		"angry"
		"apple"
		"banana"
		"beautiful"
		"sorry"
		 english_dictionary entity에
		 "미안한"	"sorry"
"바나나"	"banana"
"분개한"	"angry"
"사과"	"apple"
"수려한"	"beautiful"
"아름다운"	"beautiful"
"화난"	"angry"

가 있다고 가정한다.
		 */
		List<String> words = new ArrayList<>();
		words.add("angry");
		words.add("apple");
		words.add("banana");
		for(String word:words) {
			vocabularyRepo.save(new Vocabulary().builder().englishSpelling(spellingRepo.findById(word).get()).group(group).member(member).build());
		}
		assertEquals(vocabularyRepo.count(),3L);
		List<String> deleted = new ArrayList<>();
		deleted.add("angry");
		deleted.add("apple");
		vocabularyRepo.deleteByGroupNumberAndMemberNumberAndWordList(group.getGno(), member.getMno(), deleted);
		assertEquals(vocabularyRepo.count(),1L);
		List<Vocabulary> result = vocabularyRepo.findAll();
		assertEquals(result.get(0).getEnglishSpelling().getSpelling(),"banana");//banana만 남아야 함
	}
	@Test
	public void deleteByGroupNumberAndMemberNumberAndWordListExceptionCaseTest() {
		List<String> words = new ArrayList<>();
		words.add("angry");
		words.add("apple");
		words.add("banana");
		for(String word:words) {
			vocabularyRepo.save(new Vocabulary().builder().englishSpelling(spellingRepo.findById(word).get()).group(group).member(member).build());
		}
		assertEquals(vocabularyRepo.count(),3L);
		List<String> deleted = new ArrayList<>();
		deleted.add("angry");
		deleted.add("abcde");//단어장에 없는 단어를 삭제하려고 한다면?
		vocabularyRepo.deleteByGroupNumberAndMemberNumberAndWordList(group.getGno(), member.getMno(), deleted);
		assertEquals(vocabularyRepo.count(),2L);//angry apple banana중에서 angry만 삭제되어야 함
		List<String> result = new ArrayList<>();
		for(Vocabulary voca:vocabularyRepo.findAll()) {
			result.add(voca.getEnglishSpelling().getSpelling());
		}
		Collections.sort(result);
		assertEquals(result.get(0),"apple");
		assertEquals(result.get(1),"banana");
	}
}
