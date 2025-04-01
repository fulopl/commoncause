import {useState} from "react";

function GroupsTable({groups, onDelete}) {
    const [idToDelete, setIdToDelete] = useState(null)

    if (groups.length === 0) return (
        <h2>Currently you have no groups.</h2>
    )

    return <table>
        <thead>
        <tr>
            <th>Group ID</th>
            <th>Group name</th>
            <th>Members count</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        {
            groups.map(city => {
                return <tr key={city.id}>
                    <td>{city.id}</td>
                    <td>{city.name}</td>
                    <td>{city.countOfMembers}</td>
                    <td>
                        <button type={"button"} onClick={()=> console.log("Edit")}>
                            Edit
                        </button>
                    </td>
                    <td>{idToDelete === city.id ?
                        <>
                            <button type="button" style={{backgroundColor: "red"}}
                                    onClick={() => {
                                        setIdToDelete(null);
                                        onDelete(city.id);
                                    }}>Confirm delete
                            </button>
                            <button type="button" onClick={() => setIdToDelete(null)}>
                                Cancel
                            </button>
                        </>
                        :
                        <button type="button" onClick={() => setIdToDelete(city.id)}>
                            Delete
                        </button>
                    }
                    </td>
                </tr>
            })
        }
        </tbody>
    </table>
}

export default GroupsTable;