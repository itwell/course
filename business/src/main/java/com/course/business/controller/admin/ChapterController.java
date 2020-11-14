package com.course.business.controller.admin;


import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import com.course.server.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping("/chapter/list")
    public PageDto test(@RequestBody PageDto pageDto) {

        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/chapter/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        chapterService.save(chapterDto);
        return chapterDto;
    }
}
