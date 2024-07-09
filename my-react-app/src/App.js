import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login'; // 根据实际路径修改
import TaskManager from './TaskManager'; // 根据实际路径修改

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/tasks" element={<TaskManager />} />
                <Route path="/" element={<Login />} />
            </Routes>
        </Router>
    );
};

export default App;
