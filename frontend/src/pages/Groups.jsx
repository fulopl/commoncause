import useSWR from "swr";

const fetchGroups = (url) => {
    return fetch(url, {
        headers:
            {
                'Authorization': `Bearer ${localStorage.getItem("token")}`
            }
    }).then(resp => resp.json())
}

function Groups() {
    const {
        data: cities,
        isLoading: isFetchingGetCities,
        mutate
    } = useSWR("api/group", fetchGroups);

    return <>Groups page!</>
}

export default Groups;