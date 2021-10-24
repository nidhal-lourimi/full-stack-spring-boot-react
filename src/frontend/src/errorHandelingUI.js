import { Result, Button } from 'antd';


export const errorUI = (msg) => {

    return <>
        <Result
            status={msg}
            title={msg}
            subTitle="Sorry, something went wrong."
        />
    </>
   /* console.log( `this is working ${msg}`)*/
}



