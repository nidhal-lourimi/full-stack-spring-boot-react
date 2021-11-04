import {notification} from "antd";
import React from 'react';
import ReactDOM from 'react-dom';

const openNotificationWithIcon = (type,msg,description,placement) => {
    placement= placement || "topRight"
    notification[type]( {msg, description,placement });
};


export  const successNotification =(message,description,placement) =>
    openNotificationWithIcon('success',message,description,placement)

export  const errorNotification =(message,description,placement) =>
    openNotificationWithIcon('error',message,description,placement)

export  const warningNotification =(message,description,placement) =>
    openNotificationWithIcon('warning',message,description,placement)

export  const infoNotification =(message,description,placement) =>
    openNotificationWithIcon('info',message,description,placement)