import Loading from "../components/loading/index.js";
import GroupsTable from "../components/GroupsTable.jsx";
import {useEffect, useState} from "react";
import logout from "./Logout.jsx";
import MessageBox from "../components/MessageBox.jsx";
import {useNavigate} from "react-router-dom";

const fetchMyGroups = (url) => {
    return fetch(url, {
        headers:
            {
                'Authorization': `Bearer ${localStorage.getItem("token")}`
            }
    }).then(resp => resp.json())
}

function Groups() {
    const navigate = useNavigate();
    const [groups, setGroups] = useState([])
    const [isLoading, setLoading] = useState(true)
    const [error, setError] = useState("");

    useEffect(() => {
        fetchMyGroups("/api/group/owner").then(resp => {
            setLoading(false);
            if (resp.error) setError(resp.error);
            else setGroups(resp.sort((a, b) => a.name > b.name));
        });
    }, [])

    if (error) return <MessageBox
        text={error}
        onOk={() => navigate("/")}
    />

    if (isLoading) return <Loading/>

    return <>
        <div>
            <h2>My Groups</h2>
            <button type={"button"} onClick={() => logout("Add group")}>
                Add Group
            </button>
            {groups.length > 0 ?
                <GroupsTable
                    groups={groups}
                    onDelete={() => console.log("onDelete")}  //TODO
                />
                :
                <h2>
                    Currently you have no groups. Press the 'Add Group' button to create one.
                </h2>
            }
        </div>
    </>
}

export default Groups;