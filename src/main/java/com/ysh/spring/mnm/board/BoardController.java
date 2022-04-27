package com.ysh.spring.mnm.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.spring.mnm.common.util.file.FileInfo;
import com.ysh.spring.mnm.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("create")
    @ResponseBody
    public Board createBoard(@RequestParam("board") String board,
                             @RequestParam(value = "files", required = false) List<MultipartFile> files) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Board boardObj = mapper.readValue(board, Board.class);
        Member member = new Member();
        member.setUserIdx(12345l);
        boardObj.setMember(member);
        return boardService.persistBoard(boardObj, files);
    }

    @PutMapping("modify")
    @ResponseBody
    public Optional<Board> modifyBoard(@RequestParam("board") String board,
                                       @RequestParam(value = "deletedFiles", required = false) Long[] dFiles,
                                       @RequestParam(value = "files", required = false) List<MultipartFile> files) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Board boardObj = mapper.readValue(board, Board.class);
        return boardService.modifyBoard(boardObj, dFiles, files);
    }

    @GetMapping("list")
    public Page<Board> boardList(Pageable pageable) {
        return boardService.findBoardList(pageable);
    }

    @GetMapping("searchList")
    public Page<Board> searchList(Pageable pageable,
                                  String type,
                                  String keyword) {
        if (keyword.isEmpty()) {
            return boardService.findBoardList(pageable);
        } else {
            return boardService.findSearchList(pageable, type, keyword);
        }
    }

    @GetMapping("detail/{id}")
    @ResponseBody
    public Map<String, Object> boardDetail(@PathVariable("id") Integer idx) {
        Map<String, Object> board = boardService.findBoard(idx);
        return board;
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public String deleteBoard(@PathVariable("id") Integer idx) {
        boardService.deleteBoard(idx);
        return "good";
    }

    @PostMapping("comment/{id}")
    @ResponseBody
    public BoardComment addComment(@PathVariable("id") Long idx,
                                   @RequestBody BoardComment boardComment) {
        Member member = new Member();
        member.setUserIdx(12345l);
        boardComment.setMember(member);
        boardComment.setBdIdx(idx);
        BoardComment comment = boardService.persistBoardComment(boardComment);
        return comment;
    }

    @PutMapping("modifyComment")
    @ResponseBody
    public BoardComment modifyComment(@RequestBody BoardComment comment) {
        return boardService.modifyComment(comment);
    }

    @DeleteMapping("deleteComment/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id") Integer idx) {
        boardService.deleteComment(idx);
        return "good";
    }

    @PutMapping("recommendBoard/{id}")
    @ResponseBody
    public Board recommendBoard(@PathVariable("id") Long bdIdx) {
        return boardService.modifyBoardRecCount(bdIdx);
    }

    @PutMapping("recommendComment/{id}")
    @ResponseBody
    public BoardComment recommendComment(@PathVariable("id") Long coIdx) {
        return boardService.modifyCommentRecCount(coIdx);
    }

}
