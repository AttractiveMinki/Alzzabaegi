package com.mycom.app.service;

import com.mycom.app.domain.dto.StageDto;
import com.mycom.app.domain.entity.Stage;
import com.mycom.app.repository.GameRepository;
import com.mycom.app.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Service
public class StageServiceImpl implements StageService {

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Autowired
    StageRepository stageRepository;

    @Override
    @Transactional
    public StageDto getStageInfoByUserIdAndGameCode(String userId, int gameCode) {
        Stage stage = stageRepository.findByUserIdAndGameCode(userId, gameCode);
        return new StageDto(stage.getStageId(),stage.getUserId(), stage.getGameCode()+"",stage.getCurStage()+"");
    }

    @Override
    @Transactional
    public int updateStageInfo(StageDto stageDto) {
        try {
            Stage stage = new Stage();
            stage.setStageId(stageDto.getStageId());
            stage.setUserId(stageDto.getUserId());
            stage.setGameCode(Integer.parseInt(stageDto.getGameCode()));
            stage.setCurStage(Integer.parseInt(stageDto.getCurStage()));
            stageRepository.save(stage);
            return SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
            return FAIL;
        }
    }

}
