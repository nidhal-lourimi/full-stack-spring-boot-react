import {notification} from "antd";


const openNotificationWithIcon = (type,msg,description) => {
    notification[type]( {msg, description  });
};


export  const successNotification =(message,description) =>
    openNotificationWithIcon('success',message,description)

export  const errorNotification =(message,description) =>
    openNotificationWithIcon('error',message,description)

export  const warningNotification =(message,description) =>
    openNotificationWithIcon('warning',message,description)

export  const infoNotification =(message,description) =>
    openNotificationWithIcon('info',message,description)