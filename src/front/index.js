import React from 'react';
import ReactDOM from 'react-dom';
import RouterApp from './RouterApp.jsx';

const app = document.getElementById('app');
ReactDOM.render(<RouterApp/>, app);

module.hot.accept();
