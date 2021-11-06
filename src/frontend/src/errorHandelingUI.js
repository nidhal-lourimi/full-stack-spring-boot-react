import { Result } from 'antd';


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



