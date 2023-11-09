let BN = document.getElementById("BN");
let BC = document.getElementById("BC");
let BT = document.getElementById("BT");
let create = document.getElementById("create");
let tableBody = document.getElementById("tablebody");

create.addEventListener('click', function (e) {
    e.preventDefault();

    let bnValue = BN.value.trim().toLowerCase();
    let bcValue = BC.value.trim().toLowerCase();
    let btValue = BT.value.trim().toLowerCase();

    // Clear the table before displaying new data
    tableBody.innerHTML = '';

    fetch("https://api.openbrewerydb.org/breweries")
        .then(response => response.json())
        .then(data => {
            // console.log("hi");
            data.forEach(val => {
                // console.log(val);
                let { name, city, brewery_type, address_1, address_2 , address_3, phone, website_url, state } = val;
                if (
                    (!bnValue || name.toLowerCase().includes(bnValue)) &&
                    (!bcValue || city.toLowerCase().includes(bcValue)) &&
                    (!btValue || brewery_type.toLowerCase().includes(btValue))
                ) {
                    let tr = document.createElement("tr");
                    let com=document.createElement("p")
                    let sub=document.createElement("button")
                    tr.innerHTML = `
                        <td>${name}</td>
                        <td>${address_1},${address_2},${address_3}</td>
                        <td>${phone}</td>
                        <td><a href="${website_url}" target="_blank">View Profile</a></td>
                        
                        <td><p> <input type="text" placeholder="comment" class="input_text"> <button type="submit" class="submit_btn">comment</button></p>
                         <section class="display_review"></section></td>
                        <td>${state},${city}</td>
                       
                        
                    `;

                    let btn=document.getElementsByClassName("submit_btn");
                    let display_review=Array.from(document.getElementsByClassName("display_review"));
                    let input_val=Array.from(document.getElementsByClassName("input_text"));
                    Array.prototype.slice.call(btn).map((v,i)=>{
                        // console.log(v);
                        v.addEventListener("click",e=>{
                            display_review[i].innerHTML=input_val[i].value;
                        })
                    })
                    console.log(btn,display_review);
                    tableBody.appendChild(tr);
                }
            });

            if (tableBody.children.length === 0) {
                // No matches found, display a message
                let tr = document.createElement("tr");
                tr.innerHTML = `<td colspan="6">No breweries found</td>`;
                tableBody.appendChild(tr);
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
});