import { Result, Button } from 'antd';
import React from 'react';
import ReactDOM from 'react-dom';

export const errorUI = (msg) => {
    console.log( `this is working ${msg}`)
    return <>
        <Result
            status={msg}
            title={msg}
            subTitle="Sorry, something went wrong."
        />
    </>

}



