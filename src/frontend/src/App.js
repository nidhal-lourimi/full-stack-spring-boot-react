//import {Button,Radio} from "antd";
import React from 'react';
import ReactDOM from 'react-dom';
import {useState, useEffect} from "react";
import {deleteStudent, getAllStudents} from "./client";
import {Layout, Menu, Breadcrumb, Table, Empty, Button, Badge, Tag, Popconfirm,message,Image,Spin} from 'antd';
import {
    DesktopOutlined,
    PieChartOutlined,
    FileOutlined,
    TeamOutlined,
    UserOutlined,
    LoadingOutlined,  PlusCircleOutlined
} from '@ant-design/icons';

import StudentDrawerForm from "./StudentDrawerForm";

import './App.css';
import Avatar from "antd/es/avatar/avatar";
import {errorUI} from "./errorHandelingUI";
import Modal from "antd/es/modal/Modal";
import {errorNotification} from "./notification";

const antIcon = <LoadingOutlined style={{fontSize: 40}} spin/>;
const {Header, Content, Footer, Sider} = Layout;
const {SubMenu} = Menu;


function App() {

 window.responde="200";

    const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Gender',
        dataIndex: 'gender',
        key: 'gender',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Action',
        key: 'operation',
        fixed: 'right',
        width: 100,
        render: (students) => <Popconfirm
            title="Are you sure to delete this task?"
            onConfirm={()=>confirm(students)}
            onCancel={cancel}
            okText="Yes"
            cancelText="No"
        > <Button onClick ={()=> {console.log(students.name)}} >delete</Button> </Popconfirm>
    }

];


    function confirm( students) {
        console.log(students.id);
        deleteStudent(students.id).then(()=>{
        message.success(`${students.name} deleted`).then(fetchStudents());
      /*deleteStudent(students.id);*/
       fetchStudents();}).catch(err => {console.log(err);
            err.response.json().then(res =>
                errorNotification("there was an issue",`${res.message}[${res.status}][${res.error}] `,"bottomLeft"))})
    }
function cancel(e) {
    console.log(e);
    message.error('deleting cancelled');
}


    const [students, setStudents] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true)
    const [showDrawer, setShowDrawer] = useState(false);

    useEffect(() => {
        console.log("component is mounted");
        fetchStudents();
    }, []);

    const fetchStudents = () => getAllStudents()
        .then(res => res.json())
        .then(data => {
           /* console.log(data);*/
            setStudents(data);
            setFetching(false);

        }).catch(err => {
            console.log(err.response.status);
            let m =(err.response.status).toString();
            /*setFetching(false);*/
             window.responde=m;
            console.log(`error ${window.responde}`);
            renderStudents();
        } );


    const renderStudents = () => {
        console.log(`error 2  ${window.responde}`);
        if (fetching && (window.responde==="200"))
        {
            console.log(window.responde);
          return(  <div className="load"><Spin indicator={antIcon}/></div>);
        };
        if ((window.responde==="500")){
        Modal.error ({
            title: 'Fatal Error ',
            content: (errorUI(window.responde)),
        });

        };

        if (students.length <= 0 && fetching===true && window.responde==="200"  ) {

            return <Empty/> ;

        };



        return <>
            <StudentDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchStudents={fetchStudents}
            />
            <Table
                dataSource={students}
                columns={columns}
                bordered
                title={() =>
                    <>
                    <Button className="btn"
                        onClick={() => setShowDrawer(!showDrawer)}
                        type="primary" shape="round" icon={<PlusCircleOutlined/>} size="small">
                        Add New Student
                    </Button>
                        <p align="right" style={{margin : 0}}>
                            <Tag>Number of students </Tag>
                    <Badge
                    className="site-badge"
                    count={students.length}
                    overflowCount={9999}
                    />
                    </p>
                    </>
                }
                      pagination={{pageSize: 50}}
                      scroll={{y: 300}}
                      rowKey={(student)=> student.id}
        />;
    </>
    }
    return <Layout style={{minHeight: '100vh'}}>
        <Sider collapsible collapsed={collapsed} onCollapse={setCollapsed}>
            <div className="logo"/>
            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<PieChartOutlined/>}>
                    Option 1
                </Menu.Item>
                <Menu.Item key="2" icon={<DesktopOutlined/>}>
                    Option 2
                </Menu.Item>
                <SubMenu key="sub1" icon={<UserOutlined/>} title="User">
                    <Menu.Item key="3">Tom</Menu.Item>
                    <Menu.Item key="4">Bill</Menu.Item>
                    <Menu.Item key="5">Alex</Menu.Item>
                </SubMenu>
                <SubMenu key="sub2" icon={<TeamOutlined/>} title="Team">
                    <Menu.Item key="6">Team 1</Menu.Item>
                    <Menu.Item key="8">Team 2</Menu.Item>
                </SubMenu>
                <Menu.Item key="9" icon={<FileOutlined/>}>
                    Files
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout className="site-layout">
            <Header className="site-layout-background" style={{padding: 0}}/>
            <Content>
                <Breadcrumb style={{margin: '16px 16px'}}>
                    <Breadcrumb.Item><Avatar size="large" icon={<UserOutlined />} /></Breadcrumb.Item>
                    <Breadcrumb.Item>Bill</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-background" style={{padding: 24, minHeight: 10}}>
                    {renderStudents()}
                </div>
            </Content>
            <Footer style={{textAlign: 'center'}}>
                <Image width={75} src="https://user-images.githubusercontent.com/52574003/139816738-7d56a8bf-307c-487f-89b6-8416665c4c32.png" />
            </Footer>
        </Layout>
    </Layout>


}

export default App;
