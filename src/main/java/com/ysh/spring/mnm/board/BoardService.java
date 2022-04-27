package com.ysh.spring.mnm.board;


import com.ysh.spring.mnm.common.util.file.FileInfo;
import com.ysh.spring.mnm.common.util.file.FileRepository;
import com.ysh.spring.mnm.common.util.file.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository commentRepository;
    private final FileRepository fileRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards;
    }

    public Page<Board> findSearchList(Pageable pageable, String type, String keyword) {
        return type.equals("bdTitle") ? boardRepository.findAllByBdTitleContains(keyword, pageable) : boardRepository.findAllByMember_UserIdxLike(Long.parseLong(keyword), pageable);
    }

    public Board persistBoard(Board board, List<MultipartFile> files) {
        Board savedBoard = boardRepository.save(board);
        if (files != null) {
            saveFiles(files, savedBoard.getBdIdx());
        }
        return savedBoard;
    }

    public Map<String, Object> findBoard(Integer idx) {
        Optional<Board> board = boardRepository.findById(idx.longValue());
        List<FileInfo> files = fileRepository.findAllByTypeAndTypeIdx("board", board.get().getBdIdx());
        if (!board.isEmpty()) {
            board.get().setViewCount(board.get().getViewCount() + 1);
        }
        return Map.of("board", board.get(), "files", files);
    }

    public Optional<Board> modifyBoard(Board board, Long[] dFiles, List<MultipartFile> files) {
        Optional<Board> savedBoard = boardRepository.findById(board.getBdIdx());
        if(!savedBoard.isEmpty()){
            savedBoard.get().setBdTitle(board.getBdTitle());
            savedBoard.get().setBdContent(board.getBdContent());
            for (int i = 0; i < dFiles.length; i++) {
                fileRepository.deleteById(dFiles[i]);
            }
            if (files != null) {
                saveFiles(files, savedBoard.get().getBdIdx());
            }
        }

        return savedBoard;
    }

    public void deleteBoard(Integer idx) {
        boardRepository.deleteById(idx.longValue());
    }

    public BoardComment persistBoardComment(BoardComment boardComment) {
        if (boardComment.getPrIdx() != 0) {
            boardComment.setCmType(1);
        }
        BoardComment comment = commentRepository.save(boardComment);
        return comment;
    }

    public BoardComment modifyComment(BoardComment comment) {
        Optional<BoardComment> boardComment = commentRepository.findById(comment.getCoIdx());
        if(!boardComment.isEmpty()){
            boardComment.get().setCoContent(comment.getCoContent());
        }
        return boardComment.get();
    }

    public void deleteComment(Integer idx) {
        commentRepository.deleteById(idx.longValue());
        commentRepository.deleteAllByPrIdx(idx);
    }

    public Board modifyBoardRecCount(Long bdIdx) {
        Board board = boardRepository.findById(bdIdx).get();
        board.setRecCount(board.getRecCount()+1);
        return board;
    }

    public BoardComment modifyCommentRecCount(Long coIdx) {
        BoardComment boardComment = commentRepository.findById(coIdx).get();
        boardComment.setRecCount(boardComment.getRecCount()+1);
        return boardComment;
    }

    private void saveFiles(List<MultipartFile> files, Long idx) {
        FileUtil fileUtil = new FileUtil();
        for (MultipartFile multiFile : files) {
            FileInfo file = fileUtil.fileUpload(multiFile);
            file.setType("board");
            file.setTypeIdx(idx);
            fileRepository.save(file);
        }
    }
}
