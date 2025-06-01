package com.colvir.taskDictionary.controller;

import com.colvir.taskDictionary.dto.TaskDto;
import com.colvir.taskDictionary.service.TaskService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TaskController")
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getTasks")
    public void getTasksTest() throws Exception {
        Mockito.when(taskService.getTasks()).thenReturn(List.of(new TaskDto()));
        this.mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getTasksToday")
    public void getTasksTodayTest() throws Exception {
        Mockito.when(taskService.getTasksByDate(LocalDate.now())).thenReturn(List.of(new TaskDto()));
        this.mockMvc.perform(get("/api/tasks/today"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getTaskById")
    public void getTaskByIdTest() throws Exception {
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(new TaskDto()));
        this.mockMvc.perform(get("/api/tasks/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getTasksOnDate")
    public void getTasksOnDateTest() throws Exception {
        Mockito.when(taskService.getTasksByDate(LocalDate.parse("2000-01-01"))).thenReturn(List.of(new TaskDto()));
        this.mockMvc.perform(get("/api/tasks/onDate/2000-01-01"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("getTasksByGoalId")
    public void getTasksByGoalIdTest() throws Exception {
        Mockito.when(taskService.getTasksByGoalId(1L)).thenReturn(List.of(new TaskDto()));
        this.mockMvc.perform(get("/api/tasks/byGoalId/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void createTest(ResultMatcher resultMatcher) throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/tasks")
                                .content(String.format("{\"name\":\"%s\",\"date\":\"%s\",\"goalId\":\"%s\"}", "Test task", LocalDate.now(), 1L))
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
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(new TaskDto()));
        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/tasks")
                                .content(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"date\":\"%s\",\"goalId\":\"%s\"}", 1L, "Test task", LocalDate.now(), 1L))
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
        Mockito.when(taskService.delete(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/api/tasks/1"))
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
