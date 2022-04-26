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
        // return type.equals("bdTitle") ? boardRepository.findAllByBdTitleContains(keyword, pageable) : boardRepository.findAllByMember_UserIdxLike(Long.parseLong(keyword), pageable);
        if (type.equals("bdTitle")) {
            return boardRepository.findAllByBdTitleContains(keyword, pageable);
        } else {
//            Optional<Member> member = memberRepository.findById((long) Integer.parseInt(keyword));
//            log.info(member.get().toString());
            return boardRepository.findAllByMember_UserIdxLike(Long.parseLong(keyword), pageable);
        }
    }

    public Board persistBoard(Board board, List<MultipartFile> files) {
        Board savedBoard = boardRepository.save(board);
        if (files != null) {
            FileUtil fileUtil = new FileUtil();
            for (int i = 0; i < files.size(); i++) {
                FileInfo file = fileUtil.fileUpload(files.get(i));
                file.setType("board");
                file.setTypeIdx(savedBoard.getBdIdx());
                fileRepository.save(file);
            }
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

        savedBoard.get().setBdTitle(board.getBdTitle());
        savedBoard.get().setBdContent(board.getBdContent());
        if (dFiles.length != 0) {
            for (int i = 0; i < dFiles.length; i++) {
                fileRepository.deleteById(dFiles[i]);
            }
        }
        if (files != null) {
            FileUtil fileUtil = new FileUtil();
            for (int i = 0; i < files.size(); i++) {
                FileInfo file = fileUtil.fileUpload(files.get(i));
                file.setType("board");
                file.setTypeIdx(savedBoard.get().getBdIdx());
                fileRepository.save(file);
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
        BoardComment boardComment = commentRepository.findById(comment.getCoIdx()).get();
        boardComment.setCoContent(comment.getCoContent());
        return boardComment;
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
}
