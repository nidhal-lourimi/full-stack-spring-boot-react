import {Drawer, Input, Col, Select, Form, Row, Button, notification, Spin} from 'antd';
import {addNewStudent} from "./client";
import {LoadingOutlined, SmileOutlined} from "@ant-design/icons";
import {useState} from "react";
import {successNotification,errorNotification} from "./notification";

const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function StudentDrawerForm({showDrawer, setShowDrawer,fetchStudents}) {
    const onCLose = () => setShowDrawer(false);
    const  [submitting,setSubmitting] =useState(false)
    const onFinish = values => {
        setSubmitting(true)
        console.log(JSON.stringify(values, null, 2));
        onCLose();
        fetchStudents();
        addNewStudent(values).then(()=>{
            successNotification("Student successfully added",`${values.name} is added` );
            console.log("student added");
            setSubmitting(false);
            fetchStudents();

        }).catch(err => {console.log(err);
            err.response.json().then(res =>
            errorNotification("there was an issue",`${res.message}[${res.status}]`,"bottomLeft"))});
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Create new student"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Cancel
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="Name"
                        rules={[{required: true, message: 'Please enter student name'}]}
                    >
                        <Input placeholder="Please enter student name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="email"
                        label="Email"
                        rules={[{required: true, message: 'Please enter student email'}]}
                    >
                        <Input placeholder="Please enter student email"/>
                    </Form.Item>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="gender"
                        label="gender"
                        rules={[{required: true, message: 'Please select a gender'}]}
                    >
                        <Select placeholder="Please select a gender">
                            <Option value="Male">Male</Option>
                            <Option value="Female">Female</Option>
                            <Option value="Other">Other</Option>
                        </Select>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                {submitting  && <Spin indicator={antIcon} />}
            </Row>
        </Form>
    </Drawer>
}

export default StudentDrawerForm;