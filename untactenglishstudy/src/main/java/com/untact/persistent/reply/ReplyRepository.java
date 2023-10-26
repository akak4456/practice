package com.untact.persistent.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.reply.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyCustomRepository {

}
