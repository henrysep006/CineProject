
   /* global fetch */

$(document).ready(function () {

 function deletarFilme(id) {
            fetch(`/filme/deletar/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('ddddd');
                    window.location.href = "/listagem";
                } else {
                    alert('11111111');
                    console.error('Erro ao deletar o objeto.');
                }
            })
            .catch(error => console.error('Erro:', error));
        }
        
        
        
        
        
   });