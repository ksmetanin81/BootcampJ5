package com.colvir.taskDictionary.controller;

import com.colvir.taskDictionary.dto.GoalDto;
import com.colvir.taskDictionary.service.GoalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GoalController")
public class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GoalService goalService;

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getGoals")
    public void getGoalsTest() throws Exception {
        Mockito.when(goalService.getGoals()).thenReturn(List.of(new GoalDto()));
        this.mockMvc.perform(get("/api/goals"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getGoalById")
    public void getGoalByIdTest() throws Exception {
        Mockito.when(goalService.getGoalById(1L)).thenReturn(Optional.of(new GoalDto()));
        this.mockMvc.perform(get("/api/goals/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void createTest(ResultMatcher resultMatcher) throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/goals")
                                .content(String.format("{\"name\":\"%s\",\"deadline\":\"%s\"}", "Test goal", LocalDate.now()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(resultMatcher);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("create by admin")
    public void createByAdminTest() throws Exception {
        createTest(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("create by user")
    public void createByUserTest() throws Exception {
        createTest(status().isForbidden());
    }

    private void updateTest(ResultMatcher resultMatcher) throws Exception {
        Mockito.when(goalService.getGoalById(1L)).thenReturn(Optional.of(new GoalDto()));
        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/goals")
                                .content(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"deadline\":\"%s\"}", 1L, "Test goal", LocalDate.now()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(resultMatcher);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("update by admin")
    public void updateByAdminTest() throws Exception {
        updateTest(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("update by user")
    public void updateByUserTest() throws Exception {
        updateTest(status().isForbidden());
    }

    private void deleteTest(ResultMatcher resultMatcher) throws Exception {
        Mockito.when(goalService.delete(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/api/goals/1"))
                .andDo(print())
                .andExpect(resultMatcher);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("delete by admin")
    public void deleteByAdminTest() throws Exception {
        deleteTest(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("delete by user")
    public void deleteByUserTest() throws Exception {
        deleteTest(status().isForbidden());
    }
}
