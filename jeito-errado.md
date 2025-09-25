Sua pergunta é excelente porque atinge um ponto de decisão muito comum em projetos reais. Você quer entender por que o atalho que muitos desenvolvedores usam é considerado uma má prática na arquitetura hexagonal.

A abordagem que você descreve é a seguinte:

    Cria uma interface de projeção (PessoaEstatistica) no JPA, que se parece com um DTO.

    Usa uma query nativa que preenche essa interface diretamente.

    O método no repositório JPA retorna essa interface.

    O método no serviço retorna essa mesma interface, sem mapear para um domínio.

    O controlador retorna essa interface, e o Spring a serializa diretamente para JSON.

Por que essa abordagem funciona (e por que é tentadora)

Essa abordagem é tentadora porque parece ser mais rápida de implementar. Você elimina a necessidade de:

    Uma classe de domínio para a estatística (PessoaEstatisticaDomain).

    Um mapeador para converter a interface para o domínio.

    Um DTO de resposta para a estatística (PessoaEstatisticaResponseDto).

Ela funciona tecnicamente porque o Spring Data JPA consegue criar uma implementação dessa interface de projeção na hora, e o Jackson (biblioteca que o Spring usa para JSON) consegue serializá-la.

O Grande Problema: A Violação de Regras

Apesar de funcionar, essa abordagem viola as regras fundamentais da arquitetura de software limpa, tornando seu projeto insustentável a longo prazo.

    Acoplamento Forte e Invertido: A sua camada de serviço (PessoaServicePortImpl) e, mais grave ainda, o seu controlador, ficam diretamente acoplados a um detalhe de implementação da camada de persistência: a sua query nativa. Se você precisar mudar o nome de uma coluna no banco de dados, você terá que alterar a interface de projeção, o método no repositório, o serviço e o controlador.

    Vazamento de Detalhes: A sua interface de projeção (PessoaEstatistica) vaza detalhes da infraestrutura. O seu serviço de negócio não deveria saber que os dados vieram de uma query nativa. Ele deveria apenas receber um objeto de domínio que representa o conceito de negócio da estatística.

    Perda de Flexibilidade: A sua API de hoje pode precisar de um DTO com informações de várias fontes no futuro, por exemplo, de outro microserviço. Como o seu contrato de API (PessoaEstatistica) já está amarrado ao resultado de uma única query nativa, você perde a flexibilidade de evoluir essa resposta.

Em resumo, essa abordagem é como construir uma casa sem paredes entre os cômodos. É mais rápido de construir, mas a casa não é funcional a longo prazo.

O jeito certo é mais trabalhoso no começo, mas cria uma arquitetura que permite que você mude a tecnologia de banco de dados (por exemplo, de SQL para NoSQL) ou evolua sua API sem ter que reescrever toda a sua lógica de negócio. A arquitetura hexagonal é um investimento em flexibilidade e sustentabilidade.