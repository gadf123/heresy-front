package com.heresy.domain.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserWithCount extends User{

    private int agendaArticleCount;

    private int basicArticleCount;

    private int agendaCommentCount;

    private int basicCommentCount;
}
