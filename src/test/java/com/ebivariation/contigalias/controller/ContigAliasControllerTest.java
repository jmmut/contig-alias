/*
 * Copyright 2020 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ebivariation.contigalias.controller;

import com.ebivariation.contigalias.entities.AssemblyEntity;
import com.ebivariation.contigalias.service.AssemblyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Optional;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContigAliasControllerTest {

    private static final String ASSEMBLY_NAME = "Bos_taurus_UMD_3.1";

    private static final String ASSEMBLY_ORGANISM_NAME = "Bos taurus (cattle)";

    private static final long ASSEMBLY_TAX_ID = 9913;

    private static final String ASSEMBLY_GENBANK_ACCESSION = "GCA_000003055.3";

    private static final String ASSEMBLY_REFSEQ_ACCESSION = "GCF_000003055.3";

    private static final boolean ASSEMBLY_IS_GENBANK_REFSEQ_IDENTICAL = true;

//
//    private static final String GCF_ACCESSION_NO_CHROMOSOMES = "GCF_006125015.1";
//
//    @Autowired
//    private ContigAliasController api;
//

//
//    @Test
//    public void getAssemblyByAccessionGCFNoChromosomes() throws IOException {
//        Optional<AssemblyEntity> accession = api.getAssemblyByAccession(GCF_ACCESSION_NO_CHROMOSOMES);
//        assertTrue(accession.isPresent());
//        List<ChromosomeEntity> chromosomes = accession.get().getChromosomes();
//        assertNull(chromosomes);
//    }

    private static final String PATH_CONTROLLER_ROOT = "contig-alias";

    private static final String PATH_GET_ASSEMBLIES = "assemblies";

    @Autowired
    private MockMvc mvc;

    @Mock
    private AssemblyService service;

    @InjectMocks
    private ContigAliasController controller;

    @BeforeAll
    public void setUp() throws IOException {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        AssemblyEntity entity = new AssemblyEntity()
                .setName(ASSEMBLY_NAME)
                .setOrganism(ASSEMBLY_ORGANISM_NAME)
                .setGenbank(ASSEMBLY_GENBANK_ACCESSION)
                .setRefseq(ASSEMBLY_REFSEQ_ACCESSION)
                .setTaxid(ASSEMBLY_TAX_ID)
                .setGenbankRefseqIdentical(ASSEMBLY_IS_GENBANK_REFSEQ_IDENTICAL);

        Mockito.when(service.getAssemblyOrFetchByAccession(ASSEMBLY_GENBANK_ACCESSION)).thenReturn(Optional.of(entity));
    }

    @Test
    public void getAssemblyByAccessionGCAHavingChromosomes() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/" + PATH_CONTROLLER_ROOT +
                                     "/" + PATH_GET_ASSEMBLIES +
                                     "/" + ASSEMBLY_GENBANK_ACCESSION)
        ).andExpect(MockMvcResultMatchers.status().isOk());

//        Optional<AssemblyEntity> accession = api.getAssemblyByAccession(GCA_ACCESSION_HAVING_CHROMOSOMES);
//        assertTrue(accession.isPresent());
//        List<ChromosomeEntity> chromosomes = accession.get().getChromosomes();
//        assertNotNull(chromosomes);
//        assertFalse(chromosomes.isEmpty());
    }

}
