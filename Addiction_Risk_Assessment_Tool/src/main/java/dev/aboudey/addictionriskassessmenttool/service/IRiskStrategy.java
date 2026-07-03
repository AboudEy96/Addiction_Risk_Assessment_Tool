package dev.aboudey.addictionriskassessmenttool.service;

import dev.aboudey.addictionriskassessmenttool.mode.Answer;

public interface IRiskStrategy {
    void calculate(Answer answer);
}
